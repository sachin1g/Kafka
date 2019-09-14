//This is a serializer for USER class in procom package
package serializers;

import procom.*;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

public class CustomSerializer  implements Serializer<User> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, User user) {
        // 2 cases : if user  object is null, set id to 0 , and name to 0 and allocate space for buffer accordingly
        //if user is not null , get id and allocate 4 bytes for int id
        //if name is not null , get name convert to bytes , get the length of bytes and use it to allocate "length" bytes for the
        // data iteself ex: if id=10 and name="sudo" then 4 bytes to store id, 4 bytes to store length of byte array of "sudo" and
        //"length" bytes to store "sudo" itself
        int id =0;
        ByteBuffer buffer=null;
        byte[] serializedName = new byte[0];
        int length=0;
        if(user!=null) {
            try {
                id = user.getId();
                String name = user.getName();
                if(name != null) {
                    serializedName = name.getBytes("UTF-8");
                }else{
                    name="0";
                    serializedName = "0".getBytes("UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            length = serializedName.length;
        }else{
            id=0;
            try {
                serializedName = "0".getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            length=serializedName.length;
        }
        // 4byes for id, 4 bytes for length of name and then the name "length" bytes to store name
        buffer = ByteBuffer.allocate(4 + 4 + length);
        buffer.putInt(id);
        buffer.putInt(length);
        buffer.put(serializedName);


        return buffer.array();
    }


    @Override
    public void close() {

    }
}
