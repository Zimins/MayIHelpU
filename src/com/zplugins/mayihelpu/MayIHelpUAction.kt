package com.zplugins.mayihelpu

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.InputValidator
import com.intellij.openapi.ui.Messages

class MayIHelpUAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent?) {
        val project = event?.project
        if (project != null) {

            val text = Messages.showInputDialog(null, "Search it!", null,"", QueryValidator())
            val inputText = processText(text)
            browseBy(inputText)
        }
    }

    private fun browseBy(input: String?) {
        BrowserUtil.browse("https://www.google.co.kr/search?q=$input")
    }

    private fun processText(input: String?): String? {
        return input
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