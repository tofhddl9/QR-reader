package com.lgtm.qr_reader.model

import android.content.Intent
import com.lgtm.qr_reader.R

// TODO : need to refactor
enum class QrType(
    val iconRes: Int = 0,
    val description: String = "",
    val action: String? = null,
) {
    URL(R.drawable.ic_baseline_web_24, "웹 사이트로 이동", Intent.ACTION_VIEW), // http:, https:, www:
    TEL(R.drawable.ic_baseline_call_24, "이 번호로 전화걸기", Intent.ACTION_DIAL), // tel:
    GEO(R.drawable.ic_baseline_map_24, "지도에서 위치 보기", Intent.ACTION_VIEW), // geo:
    EMAIL(R.drawable.ic_baseline_mail_outline_24, "이메일 보내기", Intent.ACTION_SEND), // mailto:
    // TODO : VCARD("Vcard?", Intent.ACTION_VIEW),
    // TODO : SMS("문자 보내기", Intent.ACTION_SENDTO),
    TEXT(R.drawable.ic_baseline_text_fields_24, "지원하지 않는 형식이거나 일반 텍스트입니다", "")
}