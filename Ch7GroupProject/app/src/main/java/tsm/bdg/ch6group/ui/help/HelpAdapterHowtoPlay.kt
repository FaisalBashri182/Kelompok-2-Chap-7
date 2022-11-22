package tsm.bdg.ch6group.ui.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tsm.bdg.ch6group.databinding.ItemViewHowtoplayBinding

class HelpAdapterHowtoPlay : RecyclerView.Adapter<HelpAdapterHowtoPlay.HelpViewHolderAbout>() {

    class HelpViewHolderAbout(binding: ItemViewHowtoplayBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolderAbout {
        val binding = ItemViewHowtoplayBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HelpViewHolderAbout(binding)

    }

    override fun onBindViewHolder(holder: HelpViewHolderAbout, position: Int) {
    }

    override fun getItemCount(): Int = 1
}