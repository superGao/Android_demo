package com.itheima.readcontact;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private ListView lvList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvList = (ListView) findViewById(R.id.lv_list);

		ArrayList<HashMap<String, String>> contacts = readContacts();
		System.out.println("读取结果:" + contacts);

		SimpleAdapter adapter = new SimpleAdapter(this, contacts,
				R.layout.list_item, new String[] { "name", "phone" },
				new int[] { R.id.tv_name, R.id.tv_phone });
		lvList.setAdapter(adapter);
	}

	/**
	 * 读取联系人
	 */
	private ArrayList<HashMap<String, String>> readContacts() {
		ArrayList<HashMap<String, String>> contacts = new ArrayList<HashMap<String, String>>();

		ContentResolver resolver = getContentResolver();
		Uri uriRaw = Uri.parse("content://com.android.contacts/raw_contacts");// raw_contacts表的uri
		Uri uriData = Uri.parse("content://com.android.contacts/data");// data表的uri

		Cursor cursor = resolver.query(uriRaw, new String[] { "contact_id" },
				null, null, null);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				String id = cursor.getString(0);
				Cursor dataCursor = resolver.query(uriData, new String[] {
						"data1", "mimetype" }, "raw_contact_id=?",
						new String[] { id }, null);
				if (dataCursor != null) {
					HashMap<String, String> map = new HashMap<String, String>();
					while (dataCursor.moveToNext()) {
						String data = dataCursor.getString(0);
						String mimeType = dataCursor.getString(1);

						if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) {
							map.put("phone", data);// 设置手机号码
						} else if ("vnd.android.cursor.item/name"
								.equals(mimeType)) {
							map.put("name", data);// 设置名称
						}
					}

					contacts.add(map);
				}
			}
		}

		return contacts;
	}

}
