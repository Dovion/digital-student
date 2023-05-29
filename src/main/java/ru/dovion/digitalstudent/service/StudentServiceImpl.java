package ru.dovion.digitalstudent.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;
import ru.dovion.digitalstudent.model.Student;
import ru.dovion.digitalstudent.model.dto.GradeReportDto;
import ru.dovion.digitalstudent.model.dto.StudentDto;
import ru.dovion.digitalstudent.model.dto.StudentOutDto;
import ru.dovion.digitalstudent.model.dto.StudentReportDto;
import ru.dovion.digitalstudent.model.mapper.GradeMapper;
import ru.dovion.digitalstudent.model.mapper.StudentMapper;
import ru.dovion.digitalstudent.repository.GradeRepository;
import ru.dovion.digitalstudent.repository.StudentRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GradeRepository gradeRepository;


    @Override
    public StudentOutDto createStudent(StudentDto studentDto) {
        log.info("Создание нового студента в БД");
        Student student = StudentMapper.fromDto(studentDto);
        return StudentMapper.studentToOutDto(studentRepository.saveAndFlush(student));
    }

    @Override
    public StudentOutDto updateStudent(StudentDto studentDto, Long id) {
        log.info("Обновление данных студента");
        var student = studentRepository.findById(id)
                                       .orElseThrow(() -> new EntityNotFoundException(
                                               "Передан неверный идентификатор студента"));
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        student.setFirstName(studentDto.getFirstName());
        student.setSecondName(studentDto.getSecondName());
        return StudentMapper.studentToOutDto(studentRepository.saveAndFlush(student));
    }

    @Override
    public StudentOutDto getStudent(Long id) {
        log.info("Получение данных студента с идентификатором: " + id);
        var foundedStudent = studentRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор студента"));
        return StudentMapper.studentToOutDto(foundedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Удаление данных студента с идентификатором: " + id);
        var foundedStudent = studentRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор студента"));
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentOutDto> getAll() {
        log.info("Получение данных всех студентов");
        return studentRepository.findAll()
                                .stream()
                                .map(student -> StudentMapper.studentToOutDto(student))
                                .collect(Collectors.toList());
    }

    @Override
    public void makeReportForAllStudents() throws JRException, FileNotFoundException {
        List<StudentReportDto> list = studentRepository.findAll()
                                                       .stream()
                                                       .map(student -> StudentMapper.toStudentReportDto(student))
                                                       .collect(Collectors.toList());
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("CollectionBeanParam", itemsJRBean);
        InputStream inputStream =
                new FileInputStream(new File("src/main/java/ru/dovion/digitalstudent/utils/studentAllReport.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        OutputStream outputStream = new FileOutputStream(new File("all_student_report.pdf"));
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    }

    @Override
    public void makeReportForStudent(Long studentId) throws JRException, FileNotFoundException {
        var foundedStudent = studentRepository.findById(studentId)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор студента"));
        var studentFullName = foundedStudent.getFirstName() + " " + foundedStudent.getSecondName();
        List<GradeReportDto> list = gradeRepository.findAllByStudentId(studentId)
                                                   .stream()
                                                   .map(grade -> GradeMapper.toGradeReportDto(grade))
                                                   .collect(Collectors.toList());
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("CollectionBeanParam", itemsJRBean);
        params.put("Student", studentFullName);
        InputStream inputStream =
                new FileInputStream(new File("src/main/java/ru/dovion/digitalstudent/utils/studentGradeReport.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        OutputStream outputStream =
                new FileOutputStream(new File(studentFullName.replaceAll(" ", "_") + "_student_report.pdf"));
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    }
}
