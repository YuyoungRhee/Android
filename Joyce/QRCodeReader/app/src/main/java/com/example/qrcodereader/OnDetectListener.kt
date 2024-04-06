package com.example.qrcodereader

interface OnDetectListener {
    // QRCodeAnalyzer에서 QR코드가 인식됐을때 호출할 함수, 데이터 내용을 인수로 받음
    fun onDetect(msg: String)
}