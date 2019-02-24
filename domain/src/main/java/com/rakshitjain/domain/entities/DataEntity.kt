package com.rakshitjain.presentation.entities


/**
 * A generic wrapper class around data request
 */

sealed class DataEntity<RequestData>{
    class ERROR<RequestData>(var error: ErrorEntity,var data: RequestData? = null): DataEntity<RequestData>()

    class SUCCESS<RequestData>(var data: RequestData? = null): DataEntity<RequestData>()

    class LOADING<RequestData>(var data: RequestData? = null): DataEntity<RequestData>()

}