package tsm.bdg.ch6group.ui.editprofile

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tsm.bdg.ch6group.R
import tsm.bdg.ch6group.data.model.Avatar
import tsm.bdg.ch6group.databinding.ItemAvatarBinding

class EditProfileAdapter(private val avatars: MutableList<Avatar> = mutableListOf()):
    RecyclerView.Adapter<EditProfileAdapter.EditProfileViewHolder>() {

    var listener: RvAvatarSetOnClickListener? = null
    private val selectedItem = RecyclerView.NO_POSITION

    @SuppressLint("NotifyDataSetChanged")
    fun addAvatars(data: MutableList<Avatar>){
        this.avatars.clear()
        this.avatars.addAll(data)
        notifyDataSetChanged()
    }

    fun addSkin(data: Avatar){
        this.avatars.add(data)
        notifyDataSetChanged()
    }

    class EditProfileViewHolder (val binding: ItemAvatarBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(avatar: Avatar){
            Glide.with(binding.root)
                .load(avatar.avatar)
                .into(binding.ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditProfileViewHolder {
        val binding = ItemAvatarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EditProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EditProfileViewHolder, position: Int) {
        val avatarsClass = avatars[position]
        holder.binding.ivAvatar.setOnClickListener {
            listener?.onItemClicked(it, avatars[position])
        }
        holder.onBind(avatarsClass)
    }

    override fun getItemCount(): Int = avatars.size
}