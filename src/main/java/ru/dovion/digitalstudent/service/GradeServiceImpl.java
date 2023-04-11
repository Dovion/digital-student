package ru.dovion.digitalstudent.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dovion.digitalstudent.model.dto.GradeDto;
import ru.dovion.digitalstudent.model.dto.GradeOutDto;
import ru.dovion.digitalstudent.model.mapper.GradeMapper;
import ru.dovion.digitalstudent.repository.GradeRepository;
import ru.dovion.digitalstudent.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    private final StudentRepository studentRepository;

    @Override
    public GradeOutDto createGrade(GradeDto gradeDto) {
        log.info("Создание новой оценки");
        var foundedStudent = studentRepository.findById(gradeDto.getStudentId())
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор студента"));
        var grade = GradeMapper.fromDto(gradeDto);
        return GradeMapper.gradeToOutDto(gradeRepository.saveAndFlush(grade));
    }

    @Override
    public GradeOutDto updateGrade(GradeDto gradeDto, Long id) {
        log.info("Обновление оценки");
        var foundedStudent = studentRepository.findById(gradeDto.getStudentId())
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор студента"));
        var grade = gradeRepository.findById(gradeDto.getStudentId())
                                   .orElseThrow(
                                           () -> new EntityNotFoundException("Передан неверный идентификатор оценки"));
        grade.setScore(gradeDto.getScore());
        grade.setStudent(foundedStudent);
        grade.setSubject(gradeDto.getSubject());
        return GradeMapper.gradeToOutDto(gradeRepository.saveAndFlush(grade));
    }

    @Override
    public GradeOutDto getGrade(Long id) {
        log.info("Получение оценки с идентификатором: " + id);
        var foundedGrade = gradeRepository.findById(id)
                                          .orElseThrow(() -> new EntityNotFoundException(
                                                  "Передан неверный идентификатор оценки"));
        return GradeMapper.gradeToOutDto(foundedGrade);
    }

    @Override
    public void deleteGrade(Long id) {
        log.info("Удаление оценки с идентификатором: " + id);
        var foundedGrade = gradeRepository.findById(id)
                                          .orElseThrow(() -> new EntityNotFoundException(
                                                  "Передан неверный идентификатор оценки"));
        gradeRepository.deleteById(id);
    }

    @Override
    public List<GradeOutDto> getAll() {
        log.info("Получение всех оценок");
        return gradeRepository.findAll()
                              .stream()
                              .map(grade -> GradeMapper.gradeToOutDto(grade))
                              .collect(Collectors.toList());
    }
}
