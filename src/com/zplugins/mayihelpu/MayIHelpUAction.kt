package com.zplugins.mayihelpu

import com.intellij.configurationStore.archiveState
import com.intellij.ide.BrowserUtil
import com.intellij.jarRepository.services.artifactory.Endpoint
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.InputValidator
import com.intellij.openapi.ui.Messages

class MayIHelpUAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent?) {
        val project = event?.project
        if (project != null) {

            val text = Messages.showInputDialog(null, "Search it!", null, "", QueryValidator()) ?: return

//            val inputText = processText(text)
            val searchHost = getSearchHost(text)


            when (searchHost) {
                SearchHost.NAVER -> browseByNaver(text)
                SearchHost.ANDROID -> browseByAndroid(text)
                SearchHost.GREPCODE -> browseByGrepCode(text)
                else -> browseByGoogle(text)
            }
        }
    }


    private fun processText(input: String?): String? {
        input ?: return null

        if (input[0] == ':') {
            return input
        }
        return input
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

    private fun browseByGoogle(input: String?) {
        input ?: return

        BrowserUtil.browse("https://www.google.co.kr/search?q=$input")
    }

    private fun browseByAndroid(input: String) {
        BrowserUtil.browse("https://developer.android.com/s/results/?q=$input&p=%2F")
    }

    private fun browseByGrepCode(input: String) {
        BrowserUtil.browse("http://grepcode.com/search/?query=$input")
    }

    private fun browseByNaver(input: String?) {
        input ?: return

        BrowserUtil.browse("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=$input")
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