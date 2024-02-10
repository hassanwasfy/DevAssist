package com.abaferas.devassist.ui.screen.item.edititem

interface EditItemScreenInteraction {
    fun onClickBack()
    fun onNameChange(value: String)
    fun onAuthorChange(value: String)
    fun onStartDateChange(value: String)
    fun onEndDateChange(value: String)
    fun onAmountChange(value: String)
    fun onFinishedChange(value: String)
    fun onClickEdit()
    fun onClickDelete()
    fun onDismiss()
    fun onOpenStartDialog()
    fun onOpenEndDialog()
    fun isValidated(): Boolean
    fun onRetry()
    fun onDeleteDialogDismiss()
    fun onPerformDelete()

}