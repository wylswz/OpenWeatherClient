package com.xmbsmdsj.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory

class TimezoneSerializer : KSerializer<TimeZone> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Timezone", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TimeZone {
        return TimeZone.getTimeZone(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: TimeZone) {
        encoder.encodeString(value.toString())
    }

}