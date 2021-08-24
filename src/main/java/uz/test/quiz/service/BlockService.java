package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.test.quiz.dto.receive.BlockReceive;
import uz.test.quiz.entity.Block;
import uz.test.quiz.entity.Quiz;
import uz.test.quiz.entity.User;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.BlockRepository;
import uz.test.quiz.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private QuizRepository quizRepository;


    public ApiResponse save(BlockReceive receive){
        try {
            if (blockRepository.existsByName(receive.getName())) return Payload.badRequest("This block name already exists!");
            List<Quiz> quizList=new ArrayList<>();
            for (int i = 0; i <receive.getQuizIdList().size() ; i++) {
                Optional<Quiz> quiz = quizRepository.findById(receive.getQuizIdList().get(i));
                if (!quiz.isPresent()) return Payload.notFound("Quiz not found");
                quizList.add(quiz.get());
            }
            Block block= Block
                    .builder()
                    .description(receive.getDescription())
                    .name(receive.getName())
                    .limit(receive.getLimit())
                    .quizList(quizList)
                    .build();
            block=blockRepository.save(block);
            return Payload.created(block.blockToBlockSend());
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse getAll(){
        try {
            List<Block> all = blockRepository.findAllByOrderById();
            return Payload.ok(all.stream().map(Block::blockToBlockSend));
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }


//    public Block getActiveBlockByUser(User user){
//        try {
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }



}
