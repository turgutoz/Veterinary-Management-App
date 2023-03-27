package org.csystem.android.app.veterinarian

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.retrofit.RetrofitUtil
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarinarianFindByLastNameBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianFindByLastNameActivityViewModel
import retrofit2.Call
import retrofit2.Response

import com.karandev.util.retrofit.putQueue
import com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.VeterinarianAppService
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.GET_SERVICE_BASE_URL
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.IVeterinarianService
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.VETERINARIAN
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinariansInfo

import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.veterinarian.configuration.inject.annotation.service.GetServiceAuthInterceptor
import javax.inject.Inject

@AndroidEntryPoint
class VeterinarianFindByLastNameActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarinarianFindByLastNameBinding

    @Inject
    lateinit var veterinarianService: VeterinarianAppService

    @Inject
    lateinit var veterinarianMapper: com.turgutcanozdemir.android.lib.veterinarian.data.servicelib.mapper.veterinarian.VeterinarianMapper

    @Inject
    @GetServiceAuthInterceptor
    lateinit var mVeterinarianService: IVeterinarianService

    private fun responseCallback(response: Response<VeterinariansInfo>)
    {
        val vi = response.body()

        if (vi != null)
            if (vi.veterinarians.isNotEmpty()) {
                vi.veterinarians.forEach { mBinding.viewModel!!.adapter.add(it) }

                AlertDialog.Builder(this)
                    .setMessage("Do you want to save local db?")
                    .setPositiveButton("Yes, please!") {_, _ -> saveSqlDatabase(vi)}
                    .setNegativeButton("No, thanks!") {_, _ -> ;}
                    .setNeutralButton(R.string.message_text_find_by_last_name_cancel_button) {_, _ -> ;}
                    .create()
                    .show()
                //saveSqlDatabase(vi) //Bu metot doğru çalışmıyor
            }
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

    private fun existingInfoButtonCallback(pos: Int)
    {
        val diplomaNo = mBinding.viewModel!!.adapter.getItem(pos)?.diplomaNo;

        Toast.makeText(this, "$diplomaNo", Toast.LENGTH_LONG).show()
    }

    private fun connectModeResponseCallback(response: Response<VeterinarianInfo>)
    {
        if (response.code() != 200) {
            Toast.makeText(this, R.string.no_veterinarian_message, Toast.LENGTH_LONG).show()
            return
        }

        val vi = response.body()

        if (vi != null) {
            val diplomaNo = vi.diplomaNo;
            val lastName = vi.lastName
            Toast.makeText(this, "Diploma No: $diplomaNo, Last Name:$lastName", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, R.string.problem_message, Toast.LENGTH_LONG).show()
    }

    private fun connectModeFailCallback(call: Call<VeterinarianInfo>, ex: Throwable)
    {
        Toast.makeText(this, R.string.problem_try_again_message, Toast.LENGTH_LONG).show()
        Log.d("veterinarians_response", ex.message!!)
        call.cancel()
    }

    private fun connectServiceButtonAlertPositiveCallback(pos: Int)
    {
        val diplomaNo = mBinding.viewModel!!.adapter.getItem(pos)?.diplomaNo
        val call = mVeterinarianService.findByDiplomaNo(diplomaNo!!)
        call.putQueue({_, r -> connectModeResponseCallback(r)}) { c, r -> connectModeFailCallback(c, r)}
    }

    private fun onItemClickListenerCallback(pos: Int)
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.message_title_find_by_last_name)
            .setMessage(R.string.message_text_find_by_last_name)
            .setPositiveButton(R.string.message_text_find_by_last_name_connect_service_button) {_, _ -> connectServiceButtonAlertPositiveCallback(pos)}
            .setNegativeButton(R.string.message_text_find_by_last_name_use_existing_info_button) {_, _-> existingInfoButtonCallback(pos)}
            .setNeutralButton(R.string.message_text_find_by_last_name_detail_page_button) {_, _ -> veterinarianListViewItemClickCallback(pos) }
            .create().show()
    }

    private fun veterinarianListViewItemClickCallback(pos: Int)
    {
        Intent(this, VeterinarianDetailsActivity::class.java).apply {
            putExtra(VETERINARIAN,
            mBinding.veterinarianFindByLastNameActivityVeterinarians.getItemAtPosition(pos) as VeterinarianInfo)
            startActivity(this)
        }
    }

    private fun saveSqlDatabase(vi: VeterinariansInfo)
    {
        val list = vi.veterinarians.map { veterinarianMapper.toVeterinarianSaveDTO(it) }

        try {
            if (veterinarianService.saveVeterinarian(list))
                Toast.makeText(this, "saved before", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "saved in local db", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable)
        {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun initVeterinariansListView()
    {
        mBinding.veterinarianFindByLastNameActivityVeterinarians.setOnItemClickListener{_, _, pos, _ -> onItemClickListenerCallback(pos)}
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarinarian_find_by_last_name)
        mBinding.viewModel = VeterinarianFindByLastNameActivityViewModel(this)
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
        val call = mVeterinarianService.findByLastName(mBinding.viewModel!!.text)
        call.putQueue({_, r -> responseCallback(r)}) {c, r -> failCallback(c, r)}
    }

    fun exitButtonClicked() = finish()
}