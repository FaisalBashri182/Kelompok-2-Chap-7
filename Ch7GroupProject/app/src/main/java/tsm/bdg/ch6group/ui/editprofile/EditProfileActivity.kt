package tsm.bdg.ch6group.ui.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.allViews
import androidx.recyclerview.widget.LinearLayoutManager
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.data.model.Avatar
import tsm.bdg.ch6group.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity(), RvAvatarSetOnClickListener {

    private lateinit var binding: ActivityEditProfileBinding
    private var itemKey = "" // data Dinamis (diambil/dikirim dari/ke API yang merupakan key berupa string yang berisikan item spesifik dari data Items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var items = mutableListOf(
            "avatar-defult-1", // data defult yang didapat player baru
            "avatar-defult-2", // data defult yang didapat player baru
            "avatar-defult-3", // data defult yang didapat player baru
            "avatar-defult-4", // data defult yang didapat player baru
            "avatar-alien", // data yang didapat player setelah membeli di shop
            "avatar-spartan", // data yang didapat player setelah membeli di shop
            "avatar-pirates", // data yang didapat player setelah membeli di shop
        ) // data Dinamis (diambil dari API berupa list item yang dimiliki oleh player)

        val avatarSkinsAlien = Avatar(R.drawable.avatar_alien)
        val avatarSkinsPirates = Avatar(R.drawable.avatar_pirates)
        val avatarSkinsSpartan = Avatar(R.drawable.avatar_spartan)
        val listAvatar = mutableListOf(
            Avatar(R.drawable.avatar1_svgrepo_com),
            Avatar(R.drawable.avatar2_svgrepo_com),
            Avatar(R.drawable.avatar3_svgrepo_com),
            Avatar(R.drawable.avatar4_svgrepo_com)
        )

        val editProfileAdapter = EditProfileAdapter()
        binding.rvAvatar.adapter = editProfileAdapter
        binding.rvAvatar.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        editProfileAdapter.addAvatars(listAvatar)
        if ("avatar-alien" in items ) {
            Log.e("Check","avatar-alien ada")
            editProfileAdapter.addSkin(avatarSkinsAlien)
        }
        if ("avatar-spartan" in items ) {
            Log.e("Check","avatar-spartan ada")
            editProfileAdapter.addSkin(avatarSkinsSpartan)
        }
        if ("avatar-pirates" in items ) {
            Log.e("Check","avatar-pirates ada")
            editProfileAdapter.addSkin(avatarSkinsPirates)
        }

        editProfileAdapter.listener = this
    }

    override fun onItemClicked(view: View, avatar: Avatar) {

        when(avatar.avatar){
            2131165299 -> {itemKey = "avatar-defult1"}
            2131165300 -> {itemKey = "avatar-defult2"}
            2131165302 -> {itemKey = "avatar-defult3"}
            2131165303 -> {itemKey = "avatar-defult4"}
            2131165304 -> {itemKey = "avatar-alien"}
            2131165305 -> {itemKey = "avatar-pirates"}
            2131165306 -> {itemKey = "avatar-spartan"}
        }

        Log.e("clickCheck", "${avatar.avatar} berhasil di click dan itemKey : $itemKey")
    }
}