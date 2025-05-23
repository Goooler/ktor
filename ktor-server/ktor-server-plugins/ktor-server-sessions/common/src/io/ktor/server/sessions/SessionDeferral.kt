/*
 * Copyright 2014-2025 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.server.sessions

import io.ktor.server.application.ApplicationCall

/**
 * System property flag to defer session data retrieval to the point of access rather than at the start of the
 * call lifecycle.
 */
internal const val SESSIONS_DEFERRED_FLAG = "io.ktor.server.sessions.deferred"

internal expect fun isDeferredSessionsEnabled(): Boolean

/**
 * Creates a lazy loading session from the given providers.
 */
internal expect fun createDeferredSession(call: ApplicationCall, providers: List<SessionProvider<*>>): StatefulSession
