/**
 * Author: Abdul Wahid
 * Date:25/02/2024
 * Time:10:59 AM
 */
package com.studentmanagement.service.impl;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import com.studentmanagement.constant.AppConstant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.mapper.StudentMapper;
import com.studentmanagement.presentation.Response;
import com.studentmanagement.presentation.ResponseBuilder;
import com.studentmanagement.repo.StudentRepository;
import com.studentmanagement.service.StudentService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentServiceImpl implements StudentService {

	final StudentRepository studentRepository;
	final StudentMapper studentMapper;

	@Override
	@Cacheable("student")
	public Response findAll() {
		return new ResponseBuilder().message(AppConstant.SUCCESS).data(Optional.ofNullable(studentRepository.findAll())
				.map(entities -> entities.stream().map(studentMapper::entityToDTO).collect(Collectors.toList()))
				.orElse(Collections.emptyList())).build();
	}

	@Override
	@Cacheable("student")
	public Response findStudent(final long studentId) {
		return new ResponseBuilder().message(AppConstant.SUCCESS).data(studentMapper.entityToDTO(findStudentById(studentId))).build();
	}

	@Override
	@Transactional
	@Caching(evict = { @CacheEvict(value = "student", allEntries = true) })
	public Response save(final StudentDTO studentDTO) {
		return new ResponseBuilder().message(AppConstant.SUCCESS)
				.data(studentMapper.entityToDTO(studentRepository.save(studentMapper.dtoToEntity(studentDTO)))).build();
	}

	@Override
	@Transactional
	@CacheEvict(value = "student", key = "#studentDTO.id", allEntries = true)
	public Response update(final long studentId,final StudentDTO studentDTO) {
		studentDTO.setId(studentId);
		var currentStudent = this.findStudentById(studentId);
		Student toUpdateStudent = studentMapper.dtoToEntity(studentDTO);
		toUpdateStudent.setId(currentStudent.getId());
		return new ResponseBuilder().message(AppConstant.SUCCESS)
				.data(studentMapper.entityToDTO(studentRepository.save(toUpdateStudent))).build();
	}

	@Override
	@Transactional
	@CacheEvict(value = "student", key = "#studentId", allEntries = true)
	public void delete(final long studentId) {
		studentRepository.deleteById(this.findStudentById(studentId).getId());
	}

	private Student findStudentById(final long studentId) {
		return studentRepository.findById(studentId).orElseThrow(
				() -> new StudentNotFoundException(String.format("Student for the id: %s. not found", studentId)));
	}
}
