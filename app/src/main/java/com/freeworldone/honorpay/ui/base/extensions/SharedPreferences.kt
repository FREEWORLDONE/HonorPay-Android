package com.freeworldone.honorpay.ui.base.extensions

import android.content.SharedPreferences
import com.freeworldone.honorpay.appContext
import com.freeworldone.honorpay.data.enums.UserType
import com.freeworldone.honorpay.data.models.User
import java.util.*

const val SAVED_USER_ID = "USER_ID"
const val SAVED_USER_FIRST_NAME = "USER_FIRST_NAME"
const val SAVED_USER_LAST_NAME = "USER_LAST_NAME"
const val SAVED_USER_NICKNAME = "USER_NICKNAME"
const val SAVED_USER_REGION = "USER_REGION"
const val SAVED_USER_COUNTRY = "USER_COUNTRY"
const val SAVED_USER_ATTR = "USER_ATTR"
const val SAVED_USER_EMAIL = "USER_EMAIL"
const val SAVED_USER_SIG = "USER_SIG"
const val SAVED_USER_JOINED = "USER_JOINED"
const val SAVED_USER_TYPE = "USER_TYPE"
const val SAVED_USER_PUBLIC_FIGURE = "USER_PUBLIC_FIG"
const val SAVED_USER_NOTIFICATIONS = "USER_NOTIFY"
const val SAVED_USER_REMINDERS = "USER_REM"
const val SAVED_USER_HONORS_RECEIVED = "USER_HONORS_REC"
const val SAVED_USER_HONORS_AWARDED = "USER_HONORS_AW"
const val SAVED_USER_LAST_ACTIVE = "USER_LAST_ACTIVE"
const val SAVED_USER_LAST_HONOREE = "USER_LAST_HONOREE"

val prefs: SharedPreferences by lazy { appContext.getSharedPreferences("HonorPay", 0) }

fun SharedPreferences.getString(key: String): String? = getString(key, null)
fun SharedPreferences.getDate(key: String): Date? = getLong(key, 0).let { if (it == 0L) null else Date(it) }

fun SharedPreferences.save(vararg keyValues: Pair<String, Any?>) {
    edit().apply {
        for ((k, v) in keyValues) {
            when (v) {
                null -> remove(k)
                is String -> putString(k, v)
                is Int -> putInt(k, v)
                is Long -> putLong(k, v)
                is Date -> putLong(k, v.time)
                is Boolean -> putBoolean(k, v)
                is Float -> putFloat(k, v)
                else -> throw IllegalArgumentException("Invalid type for shared prefs")
            }
        }
    }.apply()
}

var SharedPreferences.user: User?
    get() {
        if(!contains(SAVED_USER_ID)) return null
        return User(
                id = getInt(SAVED_USER_ID, 0),
                firstName = getString(SAVED_USER_FIRST_NAME) ?: "",
                lastName = getString(SAVED_USER_LAST_NAME) ?: "",
                nickname = getString(SAVED_USER_NICKNAME) ?: "",
                region = getString(SAVED_USER_REGION) ?: "",
                country = getString(SAVED_USER_COUNTRY) ?: "",
                attributes = getString(SAVED_USER_ATTR) ?: "",
                email = getString(SAVED_USER_EMAIL) ?: "",
                signature = getString(SAVED_USER_SIG) ?: "",
                joined = getDate(SAVED_USER_JOINED),
                type = getInt(SAVED_USER_TYPE, 2).let { UserType.fromInt(it) },
                isPublicFigure = getBoolean(SAVED_USER_PUBLIC_FIGURE, false),
                isEmailNotificationsAllowed = getBoolean(SAVED_USER_NOTIFICATIONS, false),
                isWeeklyEmailRemindersAllowed = getBoolean(SAVED_USER_REMINDERS, false),
                honorsReceived = getInt(SAVED_USER_HONORS_RECEIVED, 0),
                honorsAwarded = getInt(SAVED_USER_HONORS_AWARDED, 0),
                lastActive = getDate(SAVED_USER_LAST_ACTIVE),
                lastHonoree = getInt(SAVED_USER_LAST_HONOREE, 0))
    }
    set(user) {
        save(
                SAVED_USER_ID to user?.id,
                SAVED_USER_FIRST_NAME to user?.firstName,
                SAVED_USER_LAST_NAME to user?.lastName,
                SAVED_USER_NICKNAME to user?.nickname,
                SAVED_USER_REGION to user?.region,
                SAVED_USER_COUNTRY to user?.country,
                SAVED_USER_ATTR to user?.attributes,
                SAVED_USER_EMAIL to user?.email,
                SAVED_USER_SIG to user?.signature,
                SAVED_USER_JOINED to user?.joined,
                SAVED_USER_TYPE to user?.type?.type,
                SAVED_USER_PUBLIC_FIGURE to user?.isPublicFigure,
                SAVED_USER_NOTIFICATIONS to user?.isEmailNotificationsAllowed,
                SAVED_USER_REMINDERS to user?.isWeeklyEmailRemindersAllowed,
                SAVED_USER_HONORS_RECEIVED to user?.honorsReceived,
                SAVED_USER_HONORS_AWARDED to user?.honorsAwarded,
                SAVED_USER_LAST_ACTIVE to user?.lastActive,
                SAVED_USER_LAST_HONOREE to user?.lastHonoree)
    }