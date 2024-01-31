package com.abaferas.devassist.data.utils

import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.FirebaseFirestoreException

suspend fun <T> wrapRequest(
    response: suspend () -> T
): T {
    return try {
        response()
    } catch (e: FirebaseFirestoreException) {
        throw e
    } catch (e: Exception){
        throw e
    }catch (e: FirebaseAuthException){
        throw e
    }catch (e: FirebaseNetworkException){
        throw e
    }catch (e: FirebaseAuthInvalidCredentialsException){
        throw e
    }catch (e: FirebaseAuthEmailException){
        throw e
    }catch (e: FirebaseException){
        throw e
    }catch (e: NoSuchElementException){
        throw e
    }
}