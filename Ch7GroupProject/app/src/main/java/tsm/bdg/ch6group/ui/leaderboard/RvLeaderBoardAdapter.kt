package tsm.bdg.ch6group.ui.leaderboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tsm.bdg.ch6group.data.local.model.Game
import tsm.bdg.ch6group.databinding.ItemRvLeaderBoardBinding


class RvLeaderBoardAdapter(private val listData: MutableList<Game>) :
    RecyclerView.Adapter<RvLeaderBoardAdapter.LeaderBoardViewHolder>() {

    class LeaderBoardViewHolder(var binding: ItemRvLeaderBoardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder {
        val view =
            ItemRvLeaderBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeaderBoardViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int) {
        val name = listData[position].name
        val win = listData[position].win



        holder.binding.tvNameItemRvHistory.text = name
        holder.binding.tvWin.text = win.toString()


    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
