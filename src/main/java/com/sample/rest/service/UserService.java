package com.sample.rest.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sample.rest.model.User;

@Service
public class UserService {
	private static Logger log = LogManager.getLogger();
	List<User> userList = null;
	
	@PostConstruct
	public void init() {
		userList = new ArrayList<>();
		User user = null;
		Scanner sc = null;
		try (FileInputStream inputStream = new FileInputStream("C:\\temp\\SampleCSVFile_1.csv");) {
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        String[] attributes = /*line.split(",")*/  line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);;
		        int id;
		        try {
		        	id =  Integer.parseInt(attributes[2]);
		        	user = new User(id, attributes[1]);
			        userList.add(user);
			        user = null;
		        }catch(NumberFormatException e) {
		        	log.warn(line);
		        }
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		log.debug("Total number of records found : {}",userList.size());    
		} catch (IOException e) {
			log.error("",e);
		} finally {
		    if (sc != null) {
		        sc.close();
		    }
		}
	}
	
	public List<User> getUsers() {
		return this.userList;
	}
	
	public List<User> getSelectedUser(String a){
		return this.userList.stream()
					.filter(u -> u.getName().toUpperCase().startsWith(a.toLowerCase()))
					.sorted()
					.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<User> getUsers(String regex) {
		return this.userList.stream()
				.filter(u ->  u.getName().toUpperCase().matches(regex))
				.sorted()
				.collect(Collectors.toList());
	}
	
	
	public static void main(String[] args) {
		List<User> userList = new ArrayList<>();
		User user = null;
		Scanner sc = null;
		try (FileInputStream inputStream = new FileInputStream("C:\\temp\\SampleCSVFile_1.csv");) {
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] attributes = /* line.split(",") */ line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				;
				int id;
				try {
					id = Integer.parseInt(attributes[2]);
					user = new User(id, attributes[1]);
					userList.add(user);
					user = null;
				} catch (NumberFormatException e) {
					log.warn(line);
				}
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
			log.debug("Total number of records found : {}", userList.size());
		} catch (IOException e) {
			log.error("", e);
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		
		List<User> l = userList.stream().filter(u ->  u.getName().toUpperCase().matches("^[N-Z].*")).collect(Collectors.toList());
		
		l.forEach(System.out::println);
		
		System.out.println(l.size());
		
		/*String s = "Blay Rozendal";
		System.out.println(s.matches("^[A-C].*"));*/
	}
}
