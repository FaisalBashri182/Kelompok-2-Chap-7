package tsm.bdg.ch6group.ui.editprofile

import android.view.View
import tsm.bdg.ch6group.data.local.model.Avatar

interface RvAvatarSetOnClickListener {
    fun onItemClicked(view: View, avatar: Avatar)
}