package com.rakshitjain.presentation.entities

/**
 * A generic wrapper class around data request
 */

sealed class Data<RequestData>{
    class SUCCESS<RequestData>(var data: RequestData? = null): Data<RequestData>()

    class ERROR<RequestData>(var error: Error,var data: RequestData? = null): Data<RequestData>()

    class LOADING<RequestData>(var data: RequestData? = null): Data<RequestData>()

}