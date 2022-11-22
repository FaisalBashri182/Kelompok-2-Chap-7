package tsm.bdg.ch6group.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tsm.bdg.ch6group.databinding.ItemRvHistoryBinding
import tsm.bdg.ch6group.data.model.Game

class RvAdapter(private val listData: MutableList<Game>) :
    RecyclerView.Adapter<RvAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(var binding: ItemRvHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ItemRvHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val name = listData[position].name
        val status = listData[position].status
        val date = listData[position].date

        holder.binding.tvNameItemRvHistory.text = "$name :"
        holder.binding.tvStatusItemRvHistory.text = status
        holder.binding.tvDateItemRvHistory.text = date

    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
