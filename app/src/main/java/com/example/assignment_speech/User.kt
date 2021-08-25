package com.example.assignment_speech

class User {
    var name: String? = null
    var stringCount: String? = null
    var password: String? = null

    constructor() {}
    constructor(name: String?, stringCount: String?, password: String?) {
        this.name = name
        this.stringCount = stringCount
        this.password = password
    }
}
