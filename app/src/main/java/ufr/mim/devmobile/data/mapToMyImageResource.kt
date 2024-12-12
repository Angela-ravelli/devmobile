package ufr.mim.devmobile.data

import androidx.annotation.DrawableRes
import ufr.mim.devmobile.R

@DrawableRes
fun String.mapToMyImageResource() : Int =
    when(this) {
        "charlie" -> { R.drawable.charlie }
        "la_face_cachee_de_margo" -> { R.drawable.la_face_cachee_de_margo }
        "le_theoreme_des_katherines" -> { R.drawable.le_theoreme_des_katherines }
        "harry_potter_tome_1" -> { R.drawable.harry_potter_tome_1 }
        "harry_potter_tome_2" -> { R.drawable.harry_potter_tome_2}
        "harry_potter_tome_3" -> { R.drawable.harry_potter_tome_3 }
        "harry_potter_tome_4" -> { R.drawable.harry_potter_tome_4 }
        "harry_potter_tome_5" -> { R.drawable.harry_potter_tome_5 }
        "harry_potter_tome_6" -> { R.drawable.harry_potter_tome_6 }
        "harry_potter_tome_7" -> { R.drawable.harry_potter_tome_7 }
        "le_cri" -> { R.drawable.le_cri }
        "l_ile_du_diable" -> { R.drawable.l_ile_du_diable }
        "noa_torson_tome_1" -> { R.drawable.noa_torson_tome_1 }
        "noa_torson_tome_2" -> { R.drawable.noa_torson_tome_2 }
        "noa_torson_tome_3" -> { R.drawable.noa_torson_tome_3 }
        "lait_et_miel" -> { R.drawable.lait_et_miel }
        "danser_sous_la_pluie" -> { R.drawable.danser_sous_la_pluie }
        "ma_maison_en_fleur" -> { R.drawable.ma_maison_en_fleur }
        "qui_es_tu_alaska" -> { R.drawable.qui_es_tu_alaska }
        "complot" -> { R.drawable.complot }
        else -> {
            -1
        }
    }