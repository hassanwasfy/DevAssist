package com.abaferas.devassist.ui.screen.item.newitem

interface NewItemScreenInteraction {
    fun onClickBack()
    fun onNameChange(value: String)
    fun onAuthorChange(value: String)
    fun onStartDateChange(value: String)
    fun onEndDateChange(value: String)
    fun onAmountChange(value: String)
    fun onFinishedChange(value: String)
    fun onClickSave()
    fun onDismiss()
    fun onOpenStartDialog()
    fun onOpenEndDialog()
    fun isValidated(): Boolean

}