package ufr.mim.devmobile.components

import ufr.mim.devmobile.data.DataList
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.model.Books

fun String.listChoice(favorites: Set<String>) : MutableList<Books> {
    val fav: MutableList<Int> = favorites.mapNotNull { it.toIntOrNull() }.toMutableList()

    var listReturn = mutableListOf<Books>()
    var listInt = mutableListOf<Int>()
    when (this) {
        "Ma bibliothèque" -> { listReturn = BookRepository.bibliotheque }
        "Livres en cours" -> { listInt = DataList.listEnCours }
        "Livres en attente" -> { listInt = DataList.listAttente }
        "Suggestions" -> { listInt = DataList.suggestion }
        "Romance" -> { listInt = DataList.romance }
        "Science-fiction" -> { listInt = DataList.science }
        "Policier" -> { listInt = DataList.policier }
        "Livres Favoris" -> { listInt = fav }
        "Livres à acheter" -> { listInt = DataList.listAcheter }
        "Livres déjà lus" -> { listInt = DataList.listLu }
        else -> { listReturn = BookRepository.bookList }
    }
    if (listReturn.isEmpty()) {
        BookRepository.bookList.forEach { book ->
            if (book.id in listInt) {
                listReturn.add(book)
            }
        }
    }

    return listReturn
}