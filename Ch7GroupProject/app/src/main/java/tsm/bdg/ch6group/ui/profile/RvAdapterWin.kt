package tsm.bdg.ch6group.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tsm.bdg.ch6group.databinding.ItemRvWinActivityProfileBinding
import tsm.bdg.ch6group.data.local.model.Game

class RvAdapterWin(private val listWin: MutableList<Game>) :
    RecyclerView.Adapter<RvAdapterWin.WinViewHolder>() {

    class WinViewHolder(var binding: ItemRvWinActivityProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinViewHolder {
        val viewWin = ItemRvWinActivityProfileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WinViewHolder(viewWin)
    }

    override fun onBindViewHolder(holder: WinViewHolder, position: Int) {

        holder.binding.win.text = listWin[position].win.toString()
        holder.binding.name.text = listWin[position].name

    }

    override fun getItemCount(): Int {
        return listWin.size
    }


}

