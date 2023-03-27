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
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfoWithFullName
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinariansInfoWithFullName
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.veterinarian.configuration.inject.annotation.service.GetServiceAuthInterceptor
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarianFindByBetweenYearBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianFindByBetweenYearActivityViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class VeterinarianFindByBetweenYearActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarianFindByBetweenYearBinding

    @Inject
    @GetServiceAuthInterceptor
    lateinit var mVeterinarianService: IVeterinarianService

    private fun responseCallback(response: Response<VeterinariansInfoWithFullName>)
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

    private fun failCallback(call: Call<VeterinariansInfoWithFullName>, ex: Throwable)
    {
        Toast.makeText(this, R.string.problem_try_again_message, Toast.LENGTH_LONG).show()
        Log.d("veterinarians_response", ex.message!!)
        call.cancel()
    }

    private fun veterinarianListViewItemClickCallback(pos: Int)
    {
        Intent(this, VeterinarianDetailsWithFullNameActivity::class.java).apply {
            putExtra(
                VETERINARIAN,
                mBinding.veterinarianFindByBetweenYearActivityVeterinarians.getItemAtPosition(pos) as VeterinarianInfoWithFullName
            )
            startActivity(this)
        }
    }

    private fun initVeterinariansListView()
    {
        mBinding.veterinarianFindByBetweenYearActivityVeterinarians.setOnItemClickListener {_, _, pos, _ -> veterinarianListViewItemClickCallback(pos)}
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarian_find_by_between_year)
        mBinding.viewModel = VeterinarianFindByBetweenYearActivityViewModel(this)
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
        val call = mVeterinarianService.findByBetweenYear(mBinding.viewModel!!.begin, mBinding.viewModel!!.end)
        call.putQueue({_, r -> responseCallback(r)}) {c, r -> failCallback(c, r)}
    }

    fun exitButtonClicked() = finish()
}