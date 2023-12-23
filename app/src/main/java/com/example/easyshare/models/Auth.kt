package com.example.easyshare.models

data class LoginRequest(private val email: String, private val mot_de_passe: String)

data class LoginResponse(private val access_token: String) {
    fun getToken(): String {
        return access_token
    }
}

data class SignupRequest
    (private val email: String,
     private val mot_de_passe: String,
     private val pseudonyme: String ,
      private val nom: String = "utilisateur",
      private val prenom: String = "utilisateur",
      private val ville: String = "ajouter votre ville",
      private val role: String = "utilisateur",
      private val statut: String = "actif",
      private val departement: String = "ajoutez votre département"
      )

data class SignupResponse(private val success: Boolean)

