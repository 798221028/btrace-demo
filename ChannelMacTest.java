import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.lang.reflect.Field;
import java.util.Map;

import static com.sun.btrace.BTraceUtils.print;
import static com.sun.btrace.BTraceUtils.printArray;
import static com.sun.btrace.BTraceUtils.println;

@BTrace
public class ChannelMacTest  {

//    @OnMethod(
//        clazz = "com.dcq.netty.server.logic.handler.DeviceDataUploadHandler",
//        method = "isUpgrade"
//    )
//    public static void ArgBtrace(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] argType) {
//
//        println(pcn);
//        println(pmn);
//        printArray(argType);
//    }

    @OnMethod(
        clazz = "com.dcq.netty.util.ChannelMacUtils"
    )
    public static void ChannelMapMac(@Self Object self) {

		Field field = BTraceUtils.field("com.dcq.netty.util.ChannelMacUtils", "channelMap");
		Map<Object, Object> macMap = (Map<Object, Object>) BTraceUtils.get(field, self);
		String argMac = BTraceUtils.Sys.$(0);
		String mac = BTraceUtils.substr(argMac, 0, BTraceUtils.length(argMac) - 1);
		println(mac);
		Object obj = BTraceUtils.get(macMap, mac);
		if (obj != null) {
			println(mac + " contains");
		} else {
			println(mac + " not contains");
		}
    }
}
