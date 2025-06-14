package org.garius.mytodo.core.presentation

import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.error_disk_full
import mytodo.composeapp.generated.resources.error_unknown
import org.garius.mytodo.core.domain.DataError


fun DataError.toUiText(): UiText {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}