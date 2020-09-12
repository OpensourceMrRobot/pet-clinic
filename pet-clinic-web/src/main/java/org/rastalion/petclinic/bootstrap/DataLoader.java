package org.rastalion.petclinic.bootstrap;

import org.rastalion.petclinic.model.*;
import org.rastalion.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Alexander");
        owner1.setLastName("Keisse");
        owner1.setAddress("123 FakeStreet");
        owner1.setCity("Fake city");
        owner1.setTelephone("111222333");

        Pet alexandersPets = new Pet();
        alexandersPets.setPetType(savedDogPetType);
        alexandersPets.setOwner(owner1);
        alexandersPets.setName("Davinci");
        alexandersPets.setBirthDate(LocalDate.now());
        owner1.getPets().add(alexandersPets);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Elliot");
        owner2.setLastName("Alderson");
        owner2.setAddress("101 FakersStreet");
        owner2.setCity("Another Fake City");
        owner2.setTelephone("333222111");

        Pet elliotsPets = new Pet();
        elliotsPets.setPetType(savedCatPetType);
        elliotsPets.setOwner(owner2);
        elliotsPets.setName("Evil Cat");
        elliotsPets.setBirthDate(LocalDate.now());
        owner2.getPets().add(elliotsPets);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(elliotsPets);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Evil kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Keanu");
        vet1.setLastName("Reeves");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Laurence");
        vet2.setLastName("Fishburne");
        vet1.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Hugo");
        vet3.setLastName("Weaving");
        vet3.getSpecialities().add(savedDentistry);

        vetService.save(vet3);

        System.out.println("Loaded Vets...");
    }
}
