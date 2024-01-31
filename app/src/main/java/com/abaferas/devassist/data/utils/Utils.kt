package com.abaferas.devassist.data.utils

import com.abaferas.devassist.ui.utils.DevAssistException
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import retrofit2.Response

suspend fun <T> wrapRequest(
    response: suspend () -> T
): T {
    return try {
        response()
    } catch (e: FirebaseFirestoreException) {
        throw e
    } catch (e: NullPointerException) {
        throw e
    } catch (e: Exception) {
        throw e
    } catch (e: FirebaseAuthException) {
        throw e
    } catch (e: FirebaseNetworkException) {
        throw e
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        throw e
    } catch (e: FirebaseAuthEmailException) {
        throw e
    } catch (e: FirebaseException) {
        throw e
    } catch (e: NoSuchElementException) {
        throw e
    }
}

suspend fun <T> wrapResponse(
    response: suspend () -> Response<T>
): T {
    return try {
        val result = withTimeout(5000L) { response() }
        if (result.isSuccessful) {
            wrapRequest { result.body() } ?: throw DevAssistException.NoData
        } else {
            throw DevAssistException.InternalServerErrorException
        }
    } catch (
        e: DevAssistException
    ) {
        throw e
    }
    catch (e: TimeoutCancellationException){
        throw e
    }
}