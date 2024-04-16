package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.repository.UserRepository;
import com.corhuila.egresadoscorhuila.service.GenerateCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GenerateCsvServiceImpl implements GenerateCsvService {

    @Autowired
    UserRepository usersRepository;

    @Override
    public String generateCsv() {
        try {
            List<Users> users = usersRepository.findAll();
            StringBuilder csvBuilder = new StringBuilder(
                    "No.Identificación,EmailInstitucional,TipoDocumento,primerNombre,segundoNombre,primerApellido,segundoApellido,telefono, email, genero, edad, fechaNacimiento, programa, ciudadRecidencia, direccionRecidencia, sedeUniversitaria, ultimoNivelFormacion, facultad, ultimoSemestre, graduado, fechaGrado, modalidad, calificacionObtenida, tituloTrabajoGrado, labora, nombreEmpresa, rolEjecuta, fechaIngreso, actividadEjecuta, rangoSalarial, tipoContrato, modalidadTrabajo, relacionFormacion");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
            for (Users user : users){
                csvBuilder.append(user.getNoIdentificacion()).append(",");
                csvBuilder.append(user.getEmailInstitucional()).append(",");
                csvBuilder.append(user.getTipoDocumento()).append(",");
                csvBuilder.append(user.getPrimerNombre()).append(",");
                csvBuilder.append(!user.getSegundoNombre().isEmpty()? user.getSegundoNombre() : null).append(",");
                csvBuilder.append(user.getPrimerApellido()).append(",");
                csvBuilder.append(!user.getSegundoApellido().isEmpty()? user.getSegundoApellido() : null).append(",");
                csvBuilder.append(user.getTelefono()).append(",");
                csvBuilder.append(user.getEmail()).append(",");
                csvBuilder.append(user.getGenero()).append(",");
                csvBuilder.append(user.getEdad()).append(",");
                csvBuilder.append(user.getFechaNacimiento()).append(",");
                csvBuilder.append(user.getPrograma()).append(",");
                csvBuilder.append(user.getCiudadRecidencia()).append(",");
                csvBuilder.append(user.getDireccionRecidencia()).append(",");
                csvBuilder.append(user.getSedeUniversitaria()).append(",");
                csvBuilder.append(user.getUltimoNivelFormacion()).append(",");
                csvBuilder.append(user.getFacultad()).append(",");
                csvBuilder.append(user.getUltimoSemestre()).append(",");
                csvBuilder.append(user.getGraduado()).append(",");
                csvBuilder.append(user.getFechaGrado()).append(",");
                csvBuilder.append(user.getModalidad()).append(",");
                csvBuilder.append(user.getCalificacionObtenida()).append(",");
                csvBuilder.append(user.getTituloTrabajoGrado()).append(",");
                csvBuilder.append(user.getLabora()).append(",");
                csvBuilder.append(!user.getNombreEmpresa().isEmpty()? user.getNombreEmpresa() : null).append(",");
                csvBuilder.append(!user.getRolEjecuta().isEmpty()? user.getRolEjecuta() : null).append(",");
                csvBuilder.append(!user.getFechaIngreso().toString().isEmpty()? user.getFechaIngreso() : null).append(",");
                csvBuilder.append(!user.getActividadEjecuta().isEmpty()? user.getActividadEjecuta() : null).append(",");
                csvBuilder.append(!user.getRangoSalarial().isEmpty()? user.getRangoSalarial() : null).append(",");
                csvBuilder.append(!user.getTipoContrato().isEmpty()? user.getTipoContrato() : null).append(",");
                csvBuilder.append(!user.getModalidadTrabajo().isEmpty()? user.getModalidadTrabajo() : null).append(",");
                csvBuilder.append(!user.getRelacionFormacion().isEmpty()? user.getRelacionFormacion() : null).append(",");
            }
            return csvBuilder.toString();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
