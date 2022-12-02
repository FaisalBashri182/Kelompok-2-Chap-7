package tsm.bdg.ch6group.ui.loginhistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tsm.bdg.ch6group.data.local.model.Login
import tsm.bdg.ch6group.databinding.ItemRvLoginHistoryBinding

class RvLoginHistoryAdapter(private val listData: MutableList<Login>) :
    RecyclerView.Adapter<RvLoginHistoryAdapter.LoginHistoryViewHolder>() {

    class LoginHistoryViewHolder(var binding: ItemRvLoginHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginHistoryViewHolder {
        val view = ItemRvLoginHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoginHistoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LoginHistoryViewHolder, position: Int) {
        val name = listData[position].name
        val time = listData[position].time
        val date = listData[position].date
        val avatar = listData[position].avatar
        val link = "https://i.ibb.co/HC5ZPgD/splash-screen1.png"

        holder.binding.avatar.load(link)
        holder.binding.tvName.text = name
        holder.binding.tvDate.text = date
        holder.binding.tvTime.text = time

    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
