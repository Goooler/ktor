/*
 * Copyright 2014-2024 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.client.tests.utils

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*

/**
 * Helper interface to test client.
 */
actual abstract class ClientLoader actual constructor(private val timeoutSeconds: Int) {
    /**
     * Perform test against all clients from dependencies.
     */
    actual fun clientTests(
        skipEngines: List<String>,
        onlyWithEngine: String?,
        retries: Int,
        block: suspend TestClientBuilder<HttpClientEngineConfig>.() -> Unit
    ): TestResult {
        val skipEnginesLowerCase = skipEngines.map { it.lowercase() }
        return if ((onlyWithEngine != null && onlyWithEngine != "js") || skipEnginesLowerCase.contains("js")) {
            runTest {}
        } else {
            testWithEngine(Js, retries = retries, timeoutMillis = timeoutSeconds * 1000L, block = block)
        }
    }

    actual fun dumpCoroutines() {
        error("Debug probes unsupported[wasm]")
    }
}
