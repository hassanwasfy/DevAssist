package com.abaferas.devassist.ui.screen.item.edititem

interface EditItemScreenInteraction {
    fun onClickBack()
    fun onNameChange(value: String)
    fun onAuthorChange(value: String)
    fun onStartDateChange(value: String)
    fun onEndDateChange(value: String)
    fun onTypeChange(value: String)
    fun onAmountChange(value: String)
    fun onFinishedChange(value: String)
    fun onProgressChange(value: String)
    fun onClickSave()
    fun onClickEdit()
    fun onClickDelete()
}