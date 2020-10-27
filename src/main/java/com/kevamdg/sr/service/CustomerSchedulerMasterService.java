package com.kevamdg.sr.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevamdg.sr.config.CommonException;
import com.kevamdg.sr.constants.CommonConstant;
import com.kevamdg.sr.controller.CommonAction;
import com.kevamdg.sr.dao.CustomerSchedulerMasterDAO;
import com.kevamdg.sr.entity.CustomerEntity;
import com.kevamdg.sr.repository.CustomerRepository;
import com.kevamdg.sr.vo.CustomerVO;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
public class CustomerSchedulerMasterService extends CommonAction<CustomerVO> {

	private static final Logger logger = LogManager.getLogger(CustomerSchedulerMasterService.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerSchedulerMasterDAO customerDAO;

	@Transactional
	public void save() throws CommonException, FileNotFoundException, IOException {

		List<CustomerVO> list = new ArrayList<>();

		File folder = new File("D:/Softwares/customer/");
		File[] listOfFiles = folder.listFiles();

		FileReader filereader = new FileReader("D:/Softwares/customer/" + listOfFiles[0].getName());

		CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

		List<String[]> allData = csvReader.readAll();

		// print Data
		for (String[] row : allData) {
			CustomerVO customerVO = new CustomerVO();
			int i = 0;
			for (String cell : row) {

				System.out.print(cell + "\t");
				if (i == 0) {
					customerVO.setCustomerCode(cell);
					i++;
				} else if (i == 1) {
					customerVO.setCustomerName(cell);
					i++;
				} else if (i == 2) {
					customerVO.setSalesOrgName(cell);
					i++;
				} else if (i == 3) {
					customerVO.setDistributionName(cell);
					i++;
				} else if (i == 4) {
					customerVO.setCityName(cell);
					i++;
				} else if (i == 5) {
					customerVO.setParentCode(cell);
					i++;
				} else if (i == 6) {
					customerVO.setParentName(cell);
					i++;
				} else if (i == 7) {
					customerVO.setPlantName(cell);
					i++;
				}
			}
			System.out.println();
			list.add(customerVO);
		}
		if (list.size() > 0) {
			for (CustomerVO customer : list) {

				Integer city = null;
				Integer sales = null;
				Integer plant = null;
				Integer dc = null;
				CustomerEntity customerEntity = new CustomerEntity();
				if (null != customer.getCustomerCode()) {
					customerEntity = customerDAO.customerCodeCheck(customer.getCustomerCode());
				}
				if (null != customer.getCityName()) {
					city = customerDAO.city(customer.getCityName());
				}
				if (null != customer.getSalesOrgName()) {
					sales = customerDAO.sales(customer.getSalesOrgName());
				}
				if (null != customer.getPlantName()) {
					plant = customerDAO.plant(customer.getPlantName());
				}
				if (null != customer.getDistributionName()) {
					dc = customerDAO.dc(customer.getDistributionName());
				}

				if (null != customerEntity) {
					if (null != city) {
						customerEntity.setCityId(city);
					}
					if (null != sales) {
						customerEntity.setSalesOrgId(sales);
					}
					if (null != plant) {
						customerEntity.setPlantId(plant);
					}
					if (null != dc) {
						customerEntity.setDistributionId(dc);
					}
					if (null != customer.getCustomerName()) {
						customerEntity.setCustomerName(customer.getCustomerName());
					}
					if (null != customer.getParentName()) {
						customerEntity.setParentName(customer.getParentName());
					}
					if (null != customer.getParentCode()) {
						customerEntity.setParentCode(customer.getParentCode());
					}

					customerEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
					customerEntity.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
					customerEntity.setUpdateBy(1);
					customerEntity.setUpdateDate(CommonConstant.getCalenderDate());

					customerRepository.save(customerEntity);

				} else {

					customerEntity = new CustomerEntity();

					if (null != city) {
						customerEntity.setCityId(city);
					}
					if (null != sales) {
						customerEntity.setSalesOrgId(sales);
					}
					if (null != plant) {
						customerEntity.setPlantId(plant);
					}
					if (null != dc) {
						customerEntity.setDistributionId(dc);
					}
					if (null != customer.getCustomerName()) {
						customerEntity.setCustomerName(customer.getCustomerName());
					}
					if (null != customer.getCustomerCode()) {
						customerEntity.setCustomerCode(customer.getCustomerCode());
					}
					if (null != customer.getParentName()) {
						customerEntity.setParentName(customer.getParentName());
					}
					if (null != customer.getParentCode()) {
						customerEntity.setParentCode(customer.getParentCode());
					}

					customerEntity.setDeleteFlag(CommonConstant.BOOLEAN_FALSE);
					customerEntity.setActiveFlag(CommonConstant.BOOLEAN_TRUE);
					customerEntity.setCreateBy(1);
					customerEntity.setCreateDate(CommonConstant.getCalenderDate());
					customerEntity.setUpdateBy(1);
					customerEntity.setUpdateDate(CommonConstant.getCalenderDate());

					customerRepository.save(customerEntity);

				}

			}

		}
		filereader.close();
		csvReader.close();

		Path temp = Files.move(Paths.get("D:\\Softwares\\customer\\" + listOfFiles[0].getName()),
				Paths.get("D:\\Softwares\\customer backup\\" + listOfFiles[0].getName()));

		if (temp != null) {
			System.out.println("File renamed and moved successfully");
		} else {
			System.out.println("Failed to move the file");
		}
	}

}
