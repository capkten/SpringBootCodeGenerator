<el-form :inline="true" :model="submitData" class="demo-form-inline" :rules="rules" ref="ruleForm">
    <el-card class="box-card">
        <div slot="header" class="header clearfix">
            <span>report_comments_news</span>
            <el-button v-if="!ischeck && !isFind" class="fr" type="primary" @click="validate('ruleForm')">提交</el-button>
            <el-button v-else class="fr" type="primary" @click="goBack">返回</el-button>
        </div>
             <el-form-item label="cn_id" prop="cnId">
                 <el-input placeholder="请输入cn_id" v-model="formData.cnId"></el-input>
             </el-form-item>
    </el-card>
</el-form>