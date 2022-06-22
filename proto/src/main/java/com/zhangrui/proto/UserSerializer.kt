package com.zhangrui.proto

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<UserModel> {


    override val defaultValue: UserModel
        get() = UserModel.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserModel {
        try {
            return UserModel.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Read Error:", exception)
        }
    }

    override suspend fun writeTo(t: UserModel, output: OutputStream) {
        t.writeTo(output)
    }
}