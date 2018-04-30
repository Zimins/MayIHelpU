package com.zplugins.mayihelpu

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.InputValidator
import com.intellij.openapi.ui.Messages

class MayIHelpUAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent?) {
        val project = event?.project
        if (project != null) {

            var input = Messages.showInputDialog(null, "Search it!", null, "", QueryValidator()) ?: return
            var command: String

            val searchHost = getSearchHost(input)
            if (hasCommand(input)) {
                command = getCommand(input)
                input = getRawInput(input)
            }


            searchHost.search(input)
        }
    }

    private fun getRawInput(input: String): String {
        val borderPos = input.indexOf(' ')
        return input.substring(borderPos + 1)
    }

    private fun hasCommand(input: String): Boolean{
        return input[0] == ':'
    }

    private fun getCommand(input: String): String {
        return input.split(" ")[0]
    }

    fun getSearchHost(input: String): SearchHost {
        val args = input.split(" ")

        return if (args[0] == ":Naver") {
            SearchHost.NAVER
        } else if (args[0] == ":Android") {
            SearchHost.ANDROID
        } else if (args[0] == ":Grepcode") {
            SearchHost.GREPCODE
        } else {
            SearchHost.GOOGLE
        }
    }

    class QueryValidator : InputValidator {
        override fun checkInput(input: String?): Boolean {
            return true
        }

        override fun canClose(p0: String?): Boolean {
            return true
        }

    }
}