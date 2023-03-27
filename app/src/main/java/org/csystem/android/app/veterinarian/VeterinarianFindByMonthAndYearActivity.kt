package org.csystem.android.app.veterinarian

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.retrofit.putQueue
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.IVeterinarianService
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.VETERINARIAN
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinariansInfo
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.veterinarian.configuration.inject.annotation.service.GetServiceAuthInterceptor
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarianFindByMonthAndYearBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianFindByMonthAndYearActivityViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class VeterinarianFindByMonthAndYearActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarianFindByMonthAndYearBinding

    @Inject
    @GetServiceAuthInterceptor
    lateinit var mVeterinarianService: IVeterinarianService

    private fun responseCallback(response: Response<VeterinariansInfo>)
    {
        val vi = response.body()

        if (vi != null)
            if (vi.veterinarians.isNotEmpty())
                vi.veterinarians.forEach { mBinding.viewModel!!.adapter.add(it) }
            else
                Toast.makeText(this, R.string.no_veterinarian_message, Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, R.string.problem_message, Toast.LENGTH_LONG).show()
    }

    private fun failCallback(call: Call<VeterinariansInfo>, ex: Throwable)
    {
        Toast.makeText(this, R.string.problem_try_again_message, Toast.LENGTH_LONG).show()
        Log.d("veterinarians_response", ex.message!!)
        call.cancel()
    }

    private fun veterinarianListViewItemClickCallback(pos: Int)
    {
        Intent(this, VeterinarianDetailsActivity::class.java).apply {
            putExtra(
                VETERINARIAN,
                mBinding.veterinarianFindByMonthAndYearActivityVeterinarians.getItemAtPosition(pos) as VeterinarianInfo
            )
            startActivity(this)
        }
    }

    private fun initVeterinariansListView()
    {
        mBinding.veterinarianFindByMonthAndYearActivityVeterinarians.setOnItemClickListener {_, _, pos, _ -> veterinarianListViewItemClickCallback(pos)}
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarian_find_by_month_and_year)
        mBinding.viewModel = VeterinarianFindByMonthAndYearActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initVeterinariansListView()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun findButtonClicked()
    {
        mBinding.viewModel!!.adapter.clear()
        val call = mVeterinarianService.findByMonthYear(mBinding.viewModel!!.year, mBinding.viewModel!!.month)
        call.putQueue({_, r -> responseCallback(r)}) {c, r -> failCallback(c, r)}
    }

    fun exitButtonClicked() = finish()
}