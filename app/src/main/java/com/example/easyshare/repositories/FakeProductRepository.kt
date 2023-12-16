package com.example.easyshare.repositories



import com.example.easyshare.dummy.FakeProductService
import com.example.easyshare.models.ProductData
import io.reactivex.rxjava3.core.Flowable;

class FakeProductRepository(private val fakeProductData: FakeProductService) {

    fun getFakeProducts():Flowable<List<ProductData>>

    {

        val dummyData = listOf(
            ProductData(1, "Vélo de Montagne", "Jean Dupont", "Un vélo robuste pour les terrains difficiles.", "2023-01-01", "Sports", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(2, "Livre de Cuisine", "Marie Durand", "Recettes faciles pour débutants.", "2022-12-15", "Livres", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(3, "Casque Audio", "Alex Martin", "Casque audio haute qualité pour mélomanes.", "2023-03-20", "Électronique", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(4, "Sac à Dos", "Laura Laine", "Sac à dos léger et résistant pour randonnées.", "2023-02-10", "Accessoires", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(5, "Tente de Camping", "David Chevalier", "Tente spacieuse pour quatre personnes.", "2022-08-05", "Camping", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(6, "Cafetière Espresso", "Sophie Dupont", "Cafetière espresso italienne classique.", "2023-01-25", "Cuisine", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(7, "Montre Connectée", "Lucas Brunet", "Montre connectée avec suivi d'activité et GPS.", "2023-04-12", "Électronique", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(8, "Jeu de Société", "Emma Petit", "Jeu de société amusant pour toute la famille.", "2022-11-30", "Jeux", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(9, "Lampe de Bureau", "Julien Moreau", "Lampe de bureau LED avec variateur d'intensité.", "2023-03-03", "Décoration", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU"),
            ProductData(10, "Kit de Jardinage", "Marine Girard", "Kit complet pour les amateurs de jardinage.", "2022-07-21", "Jardin", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY0jbtT-z4XUqN0R9yhdwhiA-h_pPRnSbnCFAfjnKITa1GxgXoHN_NMoEsM9jC72Omek0&usqp=CAU")
        )


        return Flowable.just(dummyData)
    }
}
