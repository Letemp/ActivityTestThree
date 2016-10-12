package com.example.activitytestthird.activity;

import com.example.activitytestthird.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	//声明
	TextView tvShowinfo;
	Button btnClick;
	private boolean flag=false;
	
	public Handler hand=new Handler(){
		
		public void handleMessage(Message msg){
			switch(msg.what){
			case 0x11:
				//使用Math.random()产生随机数
				int randNum=(int)(Math.random()*100)+1;
				//使用String.valueOf进行整形转换为字符串，也可以使用Integer.toString
				tvShowinfo.setText(String.valueOf(randNum));
			    break;
			case 0x12:
			    break;
			default:
			    break;
			}
			super.handleMessage(msg);
		}
	};
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//注册
		tvShowinfo=(TextView) this.findViewById(R.id.tvShowinfo);
		btnClick=(Button)this.findViewById(R.id.btnClick);
		
		System.out.println("===========================");
		//监听
		btnClick.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("------------");
				
				//判断是否发送消息
				if(flag==false){
					flag=true;
				}else{
					flag=false;
				}
				//线程
				new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						try {
							//不停循环
							while(true){
								//休眠：控制速度，数值越小越快
								Thread.sleep(300);
								Message msg=new Message();
								if(flag){
									msg.what=0x11;
								}else{
									msg.what=0x12;
								}
								// 发送消息给handleMessage方法处理 
								hand.sendMessage(msg);
								
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}).start();
				
			}
		});
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
