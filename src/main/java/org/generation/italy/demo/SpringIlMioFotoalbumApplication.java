package org.generation.italy.demo;

import java.util.HashSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aspectj.weaver.NewFieldTypeMunger;
import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoryServ;
import org.generation.italy.demo.service.CommentServ;
import org.generation.italy.demo.service.PhotoServ;
import org.generation.italy.demo.service.RoleServ;
import org.generation.italy.demo.service.TagServ;
import org.generation.italy.demo.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private PhotoServ photoS;
	
	@Autowired
	private TagServ tagS;
	
	@Autowired
	private CategoryServ catS;
	
	@Autowired
	private CommentServ comS;
	
	@Autowired
	private UserServ userS;
	
	@Autowired
	private RoleServ roleS;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tag t1 = new Tag("Funny");
		Tag t2 = new Tag("Spring");
		Tag t3 = new Tag("Christmas");
		Tag t4 = new Tag("PHP Developer");
		Tag t5 = new Tag("Java");
		Tag t6 = new Tag("Js Developer");
		Tag t7 = new Tag("Vue Developer");
		
		tagS.saveTag(t1);
		tagS.saveTag(t2);
		tagS.saveTag(t3);
		tagS.saveTag(t4);
		tagS.saveTag(t5);
		tagS.saveTag(t6);
		tagS.saveTag(t7);
		
		Category c1 = new Category("Summer - 2022");
		Category c2 = new Category("Winter - 2022");
		Category c3 = new Category("Autumn - 2022");
		Category c4 = new Category("Back-end Dev");
		Category c5 = new Category("Front-end Dev");
		
		catS.saveCategory(c1);
		catS.saveCategory(c2);
		catS.saveCategory(c3);
		catS.saveCategory(c4);
		catS.saveCategory(c5);
		
		Set<Tag> tags1 = new HashSet<>();
		Set<Tag> tags2 = new HashSet<>();
		Set<Tag> tags3 = new HashSet<>();
		Set<Tag> tags4 = new HashSet<>();
		
		Tag ta1 = tagS.finById(1).get();
		Tag ta2 = tagS.finById(2).get();
		Tag ta3 = tagS.finById(3).get();
		Tag ta4 = tagS.finById(4).get();
		Tag ta5 = tagS.finById(5).get();
		Tag ta6 = tagS.finById(6).get();
		Tag ta7 = tagS.finById(7).get();
		
		
		tags1.add(ta1);
		tags1.add(ta3);
		
		tags2.add(ta2);
		tags2.add(ta4);
		tags2.add(ta5);
		tags2.add(ta6);
		tags2.add(ta7);
		
		tags3.add(ta1);
		tags3.add(ta3);
		tags3.add(ta4);
		
		tags4.add(ta6);
		tags4.add(ta7);
		
		Set<Category> cats1 = new HashSet<>();
		Set<Category> cats2 = new HashSet<>();
		Set<Category> cats3 = new HashSet<>();
		Set<Category> cats4 = new HashSet<>();
		
		Category cat1 = catS.findbyId(1).get();
		Category cat2 = catS.findbyId(2).get();
		Category cat3 = catS.findbyId(3).get();
		Category cat4 = catS.findbyId(4).get();
		Category cat5 = catS.findbyId(5).get();
		
		cats1.add(cat4);
		cats1.add(cat5);
		
		cats2.add(cat1);
		cats2.add(cat2);
		
		cats2.add(cat5);
		cats2.add(cat4);
		
		cats3.add(cat3);
		cats3.add(cat2);
		
		cats4.add(cat4);
		cats4.add(cat1);
		
		
		Photo p1 = new Photo("Makin insta", "https://media-assets.wired.it/photos/623b0a250b209c0e26965c87/3:2/w_1620,h_1080,c_limit/Instagram%20preferiti%20seguiti.png",
				"Makin instagram as final project!", true, tags1, cats1);

		
		Photo p2 = new Photo("Makin insta dude", 
				"https://atraksolutions.it/wp-content/uploads/java-code-with-logo-Feature_1290x688_MS.jpg",
				true, tags2, cats2);
		
		Photo p3 = new Photo("Summer time", 
				"https://media.cntraveler.com/photos/5ca2606227413200230736ae/master/pass/Summer-Travel_GettyImages-1028278382.jpg",
				true, tags3, cats3);
		
		Photo p4 = new Photo("Christmas vibes", 
				"https://cdn.mos.cms.futurecdn.net/8Zw7hWD5ZaquyftsRbEmof-1200-80.jpg",
				true, tags4, cats4);
		
		Photo p5 = new Photo("Christmas time", 
				"https://cdn.mos.cms.futurecdn.net/8Zw7hWD5ZaquyftsRbEmof-1200-80.jpg",
				false, tags4, cats4);
		
		photoS.savePhoto(p1);
		photoS.savePhoto(p2);
		photoS.savePhoto(p3);
		photoS.savePhoto(p4);
		photoS.savePhoto(p5);
		
		Photo f1 = photoS.findById(1).get();
		Photo f2 = photoS.findById(2).get();
		Photo f3 = photoS.findById(3).get();
		Photo f4 = photoS.findById(4).get();
		
		Comment co1 = new Comment("Doing great man", f1);
		Comment co2 = new Comment("Keep it up dude", f1);
		Comment co3 = new Comment("*click* Noice", f1);
		
		Comment co4 = new Comment("Cool!", f2);
		Comment co5 = new Comment("wat dis", f2);
		Comment co6 = new Comment("chad", f2);
		
		Comment co7 = new Comment("w human", f3);
		Comment co8 = new Comment("wooo cool", f3);
		Comment co9 = new Comment("Repost this on *cool spam page*", f3);
		
		Comment co10 = new Comment("yo man", f4);
		Comment co11 = new Comment("looks nice", f4);
		Comment co12 = new Comment("not that bad", f4);
		
		comS.saveComment(co1);
		comS.saveComment(co2);
		comS.saveComment(co3);
		comS.saveComment(co4);
		comS.saveComment(co5);
		comS.saveComment(co6);
		comS.saveComment(co7);
		comS.saveComment(co8);
		comS.saveComment(co9);
		comS.saveComment(co10);
		comS.saveComment(co11);
		comS.saveComment(co12);
		
		Role r1 = new Role("user");
		Role r2 = new Role("admin");
		
		roleS.save(r1);
		roleS.save(r2);
		
		User u1 = new User("Gianni", "{noop}gianni", r1);
		User u2 = new User("Gianna", "{noop}gianna", r2);
		
		userS.save(u1);
		userS.save(u2);
		
	}

}
