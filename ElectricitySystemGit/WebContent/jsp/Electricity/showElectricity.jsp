<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实时电流曲线</title>
   <!-- ECharts单文件引入 -->
<script src="ch08/echarts/dist/echarts.js"></script>
<script src="ch08/jquery/jquery-1.7.2.min.js"></script>
</head>
<body>
<%-- 	Welcome to my home!!!
	<br/>
	<s:iterator var="el" value="list">
		<s:property value="#el.elvalue" />
		<br/>
		<s:property value="#el.elid" />
	</s:iterator> --%>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="sell_data" style="width:600px;height:400px"></div>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript">
	$(function(){
		//获得后台数据
		var electricity_data;//后台电流值
		
/* 		var category_data;//类型信息
		var soft_data;//软件部销售数据
		var net_data;//网络部销售数据
		var mobile_data;//移动事业部销售数据 */
		$.ajax({
/* 			 url:'/ElectricitySystem/echarts/EChartsDataServelt',  */
			async:false,
			success: function(list){
			//2018.1.5动态获取后台电流的值
		<s:iterator var="el" value="list">
		electricity_data=<s:property value="#el.elvalue" />
	     </s:iterator>
			
/* 			    electricity_data=list.elvalue;
				electricity_data=data.electricity; */
/*用来获取前端的电流值
             success: function(data){
			//2018.1.5动态获取后台电流的值
			electricity_data=data.electricity; */
			
/* 			注释2018.1.5主要作用就是注释掉静态数据，改为动态数据的获取
                category_data=data.category;
				soft_data=data.soft_data;
				net_data=data.net_data;
				mobile_data=data.mobile_data; */
				
			}
			})
        // 路径配置
        require.config({
            paths: {
                echarts: 'ch08/echarts/dist'
            }
        });    
        // 使用
        require(
            [
                'echarts',
/*                 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载 */
				'echarts/chart/line' ,   //使用折线图加载line块
				'echarts/chart/bar'    //使用折线图加载line块
				
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('sell_data')); 
                
                var option = {
                        title: {  
                            text: '电流实时变化值'  
                        },
                      //提示框，鼠标悬浮交互时的信息提示
                    tooltip: {
                        show: true
                    },
					
					toolbox:{//定义工具按钮
						show : true,
						feature : {
							//数据信息按钮
							dataView : {show: true, readOnly: false},
							//显示折线、柱状图等切换按钮
							magicType : {show: true, 
								type: ['line', 'bar','stack', 'tiled']},
					        restore:{},
						    saveAsImage: {}
						}
					},
                    legend: {//图例
                        data:['电流值']
/* 2018.1.5号显示的数据					data:['软件部','网络板块','移动事业部'] */
                    },
                    xAxis : [//x轴设置
                        {
                        	//x轴显示类别,时间信息
                            type : 'time',
                            splitLine: {
                                show: false
                            }
/*    2018.1.5时间显示的问题编写                         data : category_data//["第一季度","第二季度","第三季度","第四季度"] */
                        }
                    ],
                    yAxis : [//y轴设置
                        {
                        	///y轴显示数据值
                            type : 'value',
                            boundaryGap: [0, '100%'],
                            splitLine: {
                                show: false
                            }
                            
                        }
                    ],
                    series : [
                    	
						 {
	                            "name":"电流值",
	                            "type":"line",
	                            "data":electricity_data//[20, 60, 30, 15],从后台函数中获取出来的电流值
	                        }
						 
/*  2018.1.5对应legend中数据的对应值                       {
                            "name":"软件部",
                            "type":"bar",
                            "data":soft_data//[80, 300, 200, 100]
                        },
						 {
                            "name":"网络板块",
                            "type":"bar",
                            "data":net_data//[50, 200, 120, 200]
                        },
						 {
                            "name":"移动事业部",
                            "type":"bar",
                            "data":mobile_data//[20, 60, 30, 15]
                        } */
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
		
	});
    </script>
</body>
</html>