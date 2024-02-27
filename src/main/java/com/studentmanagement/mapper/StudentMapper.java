package com.studentmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Author: Abdul Wahid
 * Date:23/02/2024
 * Time:10:13 PM
 */

import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "age", target = "age")
	StudentDTO entityToDTO(Student entity);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "age", target = "age")
	Student dtoToEntity(StudentDTO dto);
}
