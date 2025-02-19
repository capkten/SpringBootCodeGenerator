<el-form :inline="true" :model="submitData" class="demo-form-inline" :rules="rules" ref="ruleForm">
    <el-card class="box-card">
        <div slot="header" class="header clearfix">
            <span>report_comments_posts</span>
            <el-button v-if="!ischeck && !isFind" class="fr" type="primary" @click="validate('ruleForm')">提交</el-button>
            <el-button v-else class="fr" type="primary" @click="goBack">返回</el-button>
        </div>
             <el-form-item label="cp_id" prop="cpId">
                 <el-input placeholder="请输入cp_id" v-model="formData.cpId"></el-input>
             </el-form-item>
    </el-card>
</el-form>