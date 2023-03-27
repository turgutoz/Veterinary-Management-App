package org.csystem.android.app.veterinarian

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.api.VETERINARIAN
import com.turgutcanozdemir.android.lib.veterinarian.veterinarianlib.data.entity.VeterinarianInfo
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarianDetailsBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianDetailsActivityViewModel

class VeterinarianDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarianDetailsBinding

    private fun arrangeMiddleNameViewVisibility()
    {
        /*
        if (mBinding.viewModel!!.veterinarianInfo.middleName == null) {
            mBinding.editTextMiddleNameNullable.visibility = View.GONE
            mBinding.textViewMiddleNameNullable.visibility = View.GONE
        }

         */
    }

    private fun initVeterinarianInfo()
    {
        mBinding.viewModel!!.veterinarianInfo = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(VETERINARIAN) as VeterinarianInfo
        else
            intent.getSerializableExtra(VETERINARIAN, VeterinarianInfo::class.java)!!
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarian_details)
        mBinding.viewModel = VeterinarianDetailsActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initVeterinarianInfo()
        arrangeMiddleNameViewVisibility()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}