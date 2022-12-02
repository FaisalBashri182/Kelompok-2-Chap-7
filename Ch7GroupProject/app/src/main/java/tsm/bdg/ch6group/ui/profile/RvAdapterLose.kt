package tsm.bdg.ch6group.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tsm.bdg.ch6group.databinding.ItemRvWinActivityProfileBinding
import tsm.bdg.ch6group.data.local.model.Game

class RvAdapterLose(private val listLose: MutableList<Game>) :
    RecyclerView.Adapter<RvAdapterLose.LoseViewHolder>() {

    class LoseViewHolder(var binding1: ItemRvWinActivityProfileBinding) :
        RecyclerView.ViewHolder(binding1.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoseViewHolder {
        val viewLose = ItemRvWinActivityProfileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoseViewHolder(viewLose)
    }

    override fun onBindViewHolder(holder: LoseViewHolder, position: Int) {

        holder.binding1.lose.text = listLose[position].lose.toString()
        holder.binding1.name.text = listLose[position].name

    }

    override fun getItemCount(): Int {
        return listLose.size
    }


}

