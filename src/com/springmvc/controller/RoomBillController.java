package com.springmvc.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.manager.RoomBillManager;
import com.springmvc.model.ElectricBill;
import com.springmvc.model.RoomBill;
import com.springmvc.model.WaterBill;

@Controller
public class RoomBillController {
	@RequestMapping(value = "/roombilluser", method = RequestMethod.GET)
	public String doRoomBillUser(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		String roomid = request.getParameter("roomid");
		RoomBillManager manage = new RoomBillManager();
		Vector<RoomBill> bill = manage.getRoomBill(roomid);
		request.setAttribute("bill", bill);
		return "listroombilluser";
	}
	
	@RequestMapping(value = "/roombilladmin", method = RequestMethod.GET)
	public String doRoomBillAdmin(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		String roomid = request.getParameter("roomid");
		RoomBillManager manage = new RoomBillManager();
		Vector<RoomBill> bill = manage.getRoomBill(roomid);
		request.setAttribute("bill", bill);
		request.setAttribute("roomid", roomid);
		return "listroombilladmin";
	}
	
	@RequestMapping(value = "/addRoombill", method = RequestMethod.POST)
	public String addRoomBillAdmin(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		String roomid = request.getParameter("roomid");
		RoomBillManager manage = new RoomBillManager();
		RoomBill bill = new RoomBill();
		ElectricBill electric = new ElectricBill();
		WaterBill water = new WaterBill();
		
		java.util.Date curr_date = new java.util.Date();
		bill.setDate(new Date(curr_date.getTime()));
		bill.setRoom_billid(roomid+"/"+bill.getDate());
		bill.setRoom_cost(Integer.parseInt((String)request.getParameter("roomcost")));
		bill.setRoom_id(roomid);
		bill.setStatus("????????");
		bill.setTypeid((String)request.getParameter("typeid"));
		
		electric.setBillid(bill.getRoom_billid());
		electric.setDates(new Date(curr_date.getTime()));
		electric.setLast_unit(Integer.parseInt((String)request.getParameter("electricbilllast")));
		electric.setCurrent_unit(Integer.parseInt((String)request.getParameter("electricbillcurrent")));
		electric.setAmount(electric.calElectricAmount());
		electric.setPrice(electric.calElectricPrice());
		
		water.setBillid(bill.getRoom_billid());
		water.setDates(new Date(curr_date.getTime()));
		water.setLast_unit(Integer.parseInt((String)request.getParameter("waterbilllast")));
		water.setCurrent_unit(Integer.parseInt((String)request.getParameter("waterbillcurrent")));
		water.setAmount(water.calWaterAmount());
		water.setPrice(water.calWaterPrice());
		
		bill.setElectric_bill(electric);
		bill.setWater_bill(water);
		bill.setPrice(bill.calPrice());
		
		manage.addRoomBill(bill);
		
		Vector<RoomBill> listbill = manage.getRoomBill(roomid);
		request.setAttribute("bill", listbill);
		request.setAttribute("roomid", roomid);
		return "listroombilladmin";
	}
	
	@RequestMapping(value = "/confirmbill", method = RequestMethod.GET)
	public String doConfirmRoomBillAdmin(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		String room_billid = request.getParameter("billid");
		String roomid = request.getParameter("roomid");
		RoomBillManager manage = new RoomBillManager();
		manage.confirmRoomBill(room_billid);
		
		Vector<RoomBill> bill = manage.getRoomBill(roomid);
		request.setAttribute("bill", bill);
		request.setAttribute("roomid", roomid);
		return "listroombilladmin";
	}
	
	@RequestMapping(value = "/deleteroombill", method = RequestMethod.GET)
	public String doDeleteRoomBillAdmin(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		String room_billid = request.getParameter("billid");
		String roomid = request.getParameter("roomid");
		RoomBillManager manage = new RoomBillManager();
		manage.deleteRoomBill(room_billid);
		
		Vector<RoomBill> bill = manage.getRoomBill(roomid);
		request.setAttribute("bill", bill);
		request.setAttribute("roomid", roomid);
		return "listroombilladmin";
	}
}