package pu.zajhhaptaueuh.ztanphsop.utils

/* Copyright (C) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */

enum class Matcher(val regex: Regex) {
    Empty(Regex("^$")),
    NoSpecialCharacters(Regex("[a-zA-Z0-9]+")),
    LessThan4(Regex("^.{1,3}")),
    MoreThan12(Regex("^.{11,}$")),
    NumberLessThan4(Regex("^([0-3])")),
    NumberGreaterThan99(Regex("^([1-9]\\d\\d|[1-9]\\d{3,})$"))
}