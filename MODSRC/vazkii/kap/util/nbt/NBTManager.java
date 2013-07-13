/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 11:12:09 (GMT)]
 */
package vazkii.kap.util.nbt;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;

public final class NBTManager {

	public static void loadType(NBTTagCompound cmp, Object o) {
		Class type = o.getClass();

		List<Field> fields = new ArrayList();
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		Class supertype = type;
		while((supertype = supertype.getSuperclass()) != null)
			fields.addAll(Arrays.asList(supertype.getDeclaredFields()));

		for(Field f : fields) {
			int modifiers = f.getModifiers();
			if(Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers))
				continue;

			f.setAccessible(true);

			NBTManaged managed = f.getAnnotation(NBTManaged.class);
			if(managed != null) {
				try {
					String tag = managed.value();

					if(cmp.hasKey(tag))
						loadManagedValue(cmp, tag, f, o);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void writeType(NBTTagCompound cmp, Object o) {
		Class type = o.getClass();

		List<Field> fields = new ArrayList();
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		Class supertype = type;
		while((supertype = supertype.getSuperclass()) != null)
			fields.addAll(Arrays.asList(supertype.getDeclaredFields()));

		for(Field f : fields) {
			int modifiers = f.getModifiers();
			if(Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers))
				continue;

			f.setAccessible(true);

			NBTManaged managed = f.getAnnotation(NBTManaged.class);

			if(managed != null) {
				try {
					String tag = managed.value();

					writeManagedValue(cmp, tag, f.get(o));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void writeManagedValue(NBTTagCompound cmp, String tag, Object value) {
		if(value instanceof Byte)
			cmp.setByte(tag, (Byte) value);
		else if(value instanceof Short)
			cmp.setShort(tag, (Short) value);
		else if(value instanceof Integer)
			cmp.setInteger(tag, (Integer) value);
		else if(value instanceof Long)
			cmp.setLong(tag, (Long) value);
		else if(value instanceof Float)
			cmp.setFloat(tag, (Float) value);
		else if(value instanceof Double)
			cmp.setDouble(tag, (Double) value);
		else if(value instanceof byte[])
			cmp.setByteArray(tag, (byte[]) value);
		else if(value instanceof String)
			cmp.setString(tag, (String) value);
		else if(value instanceof int[])
			cmp.setIntArray(tag, (int[]) value);
		else {
			NBTTagCompound cmp1 = new NBTTagCompound();
			writeType(cmp1, value);
			cmp.setCompoundTag(tag, cmp1);
		}
	}

	private static void loadManagedValue(NBTTagCompound cmp, String tag, Field field, Object type) throws Exception {
		Class fieldType = field.getType();

		if(fieldType.isAssignableFrom(byte.class))
			field.set(type, cmp.getByte(tag));
		else if(fieldType.isAssignableFrom(short.class))
			field.set(type, cmp.getShort(tag));
		else if(fieldType.isAssignableFrom(int.class))
			field.set(type, cmp.getInteger(tag));
		else if(fieldType.isAssignableFrom(long.class))
			field.set(type, cmp.getLong(tag));
		else if(fieldType.isAssignableFrom(float.class))
			field.set(type, cmp.getFloat(tag));
		else if(fieldType.isAssignableFrom(double.class))
			field.set(type, cmp.getDouble(tag));
		else if(fieldType.isAssignableFrom(byte[].class))
			field.set(type, cmp.getByteArray(tag));
		else if(fieldType.isAssignableFrom(String.class))
			field.set(type, cmp.getString(tag));
		else if(fieldType.isAssignableFrom(int[].class))
			field.set(type, cmp.getIntArray(tag));
		else {
			NBTTagCompound cmp1 = cmp.getCompoundTag(tag);
			loadType(cmp1, type);
		}
	}
}
