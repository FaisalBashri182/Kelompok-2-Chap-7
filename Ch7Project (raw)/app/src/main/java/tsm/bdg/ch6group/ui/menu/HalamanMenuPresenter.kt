package tsm.bdg.ch6group.ui.menu

import tsm.bdg.ch6group.databinding.ActivityHalamanMenuBinding

class HalamanMenuPresenter(private val binding: ActivityHalamanMenuBinding,private val view: HalamanMenuView) {

    fun click(){
        binding.ivPlayerVsCPU.setOnClickListener {
            view.onCPU()
        }
        binding.ivPlayerVsPlayer2.setOnClickListener {
            view.onPlayer()
        }
    }
}